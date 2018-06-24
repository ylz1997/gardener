package com.jw.consult.controller.consult;

import com.github.pagehelper.StringUtil;
import com.jw.base.BaseFormateter;
import com.jw.base.Constants;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.consult.bean.TKmConsultApply;
import com.jw.consult.bean.TKmConsultApplyVO;
import com.jw.consult.bean.TKmConsultKeys;
import com.jw.consult.bean.TKmConsultTmplt;
import com.jw.consult.controller.aop.ResultFormatter;
import com.jw.consult.service.IKmConsultSV;
import com.jw.consult.service.IKmConsultTmpltSV;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

import static com.jw.base.Constants.JWCONSULT_TEST_DATA.TEST_REGN_ID;

/**
 * 咨询表操作入口
 *
 * @author ylz1997  2018-02-28 10:57:01
 */
@RestController
@RequestMapping("/kmConsult")
public class KmConsultController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KmConsultController.class);

    @Autowired
    private IKmConsultSV kmConsultSV;

    /*@Autowired
    private UserComponent userComponent;*/

/*    @Autowired
    private CsfManager csfManager;*/

/*    @Autowired
    private ConsultComponent consultComponent;*/

    @Autowired
    private IKmConsultTmpltSV kmConsultTmpltSV;

    /**
     * 调用搜索引擎查询咨询表信息
     *
     * @return
     * @throws GeneralException
     */
/*    @RequestMapping(value = "/getConsult", method = RequestMethod.GET)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String getConsult(String consultNm, String regnId, Integer page, Integer limit) throws GeneralException{

        Integer newPage = page;
        if(page == null){
            newPage = 1;
        }

        Integer newLimit = limit;
        if(newLimit == null){
            newLimit = 10;
        }

        params.put("indexType","consult_info");
        params.put("page", newPage);
        params.put("size", newLimit);
        params.put("indexName","ngkm.consult_info");
        params.put("sortField", "modeTime");
        params.put("aggregationField", "consultId.keyword,consultName.keyword,rangeId.keyword");

        List<Map<String, Object>> paramList = new ArrayList<>();

        if(StringUtil.isNotEmpty(consultNm)){
            Map<String, Object> temp = new HashMap<>();
            temp.put("column", "consultName");
            temp.put("type", "like");
            temp.put("value", consultNm);

            paramList.add(temp);
        }

        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("column", "rangeId");
        tempMap.put("type", "any,array");
        if(StringUtil.isEmpty(regnId)){
            tempMap.put("value", userComponent.getUserInfoSession().getProvnce());
        }else{
            tempMap.put("value", regnId);
        }
        paramList.add(tempMap);

        params.put("searchInfo", "consultId=1");
        inputObject.setBeans(paramList);
        inputObject.setServiceCode(Constants.CSF_SERVICE_CODE.NGKMSEARCH_BUSISEARCHERINTERSERVICE_POST);

        CsfOutObject csfOutObject = csfManager.pubMethod(inputObject);

        String object = csfOutObject.getObject();
        if(StringUtil.isEmpty(object)){
            return result;
        }
        Map<String, Object> parse1 = (Map)JSONUtils.parse(object);

        Integer total = (Integer)parse1.get("total");
        result.getBean().setTotal(total);

        //格式化结果数据
        List<TKmConsultApply> document = null;
        if(total != 0){
            document = formatData((List<LinkedHashMap<String, Object>>) parse1.get("document"));
        }
        result.setBeans(document);
        return result;
    }*/

    /**
     * 查询咨询表列表
     *
     * @param tKmConsultApplyVO 查询条件
     * @return 结果列表
     * @throws GeneralException
     */
    @RequestMapping(value = "/getConsultList", method = RequestMethod.GET)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String listConsultApply(TKmConsultApplyVO tKmConsultApplyVO, Integer start, Integer length, int draw) throws GeneralException{
        if(tKmConsultApplyVO == null){
            throw new GeneralException("124101");
        }

        tKmConsultApplyVO.setRegnId(Constants.JWCONSULT_TEST_DATA.TEST_REGN_ID);
        int page = start/length + 1;
        Map<String, Object> consultList = kmConsultSV.listConsultApply(tKmConsultApplyVO, page, length);
        HashMap result = new HashMap();

        result.put("data",consultList.get("list"));
        result.put("recordTotal",consultList.get("total"));
        result.put("recordsFiltered",consultList.get("total"));
        result.put("draw", draw);

        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 新增咨询表记录
     *
     * @param tKmConsultApply 内容
     * @throws GeneralException
     */
    @RequestMapping(value = "/addConsult", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void addConsult(TKmConsultApply tKmConsultApply) throws GeneralException{
        if(tKmConsultApply == null){
            throw new GeneralException("124102");
        }
        if(StringUtil.isEmpty(tKmConsultApply.getCnslNm()) || tKmConsultApply.getTmpltId() == null
                || StringUtil.isEmpty(tKmConsultApply.getRmk())){
            throw new GeneralException("124103");
        }
        tKmConsultApply.setOpPrsnId(Constants.JWCONSULT_TEST_DATA.TEST_USER);
        tKmConsultApply.setRegnId(Constants.JWCONSULT_TEST_DATA.TEST_REGN_ID);
        tKmConsultApply.setTenantId(Constants.JWCONSULT_TEST_DATA.TEST_TEND_ID);
        Long aLong = kmConsultSV.addConsult(tKmConsultApply);
        
        /*consultComponent.createConsultIndex(aLong.toString());*/
    }

    /**
     * 更新咨询表
     *
     * @param tKmConsultApply
     * @throws GeneralException
     */
    @RequestMapping(value = "/updateConsult", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void updateConsult(TKmConsultApply tKmConsultApply) throws GeneralException{
        if(tKmConsultApply == null){
            throw new GeneralException("124102");
        }
        if(StringUtil.isEmpty(tKmConsultApply.getCnslNm()) || tKmConsultApply.getTmpltId() == null
                || StringUtil.isEmpty(tKmConsultApply.getRmk())){
            throw new GeneralException("124103");
        }
        tKmConsultApply.setOpPrsnId(Constants.JWCONSULT_TEST_DATA.TEST_USER);
        tKmConsultApply.setRegnId(Constants.JWCONSULT_TEST_DATA.TEST_REGN_ID);
        tKmConsultApply.setTenantId(Constants.JWCONSULT_TEST_DATA.TEST_TEND_ID);

        if(tKmConsultApply.getCnslId() == null){
            throw new GeneralException("124104");
        }

       /* tKmConsultApply.setOpPrsnId(userComponent.getUserInfoSession().getStaffCode());
        tKmConsultApply.setRegnId(userComponent.getUserInfoSession().getProvnce());
        tKmConsultApply.setTenantId(userComponent.getUserInfoSession().getTenantId());
*/
        kmConsultSV.updateConsult(tKmConsultApply);
        /*consultComponent.createConsultIndex(tKmConsultApply.getCnslId().toString());*/
    }

    /**
     * 查询单个咨询表信息
     *
     * @return
     * @throws
     */
    @RequestMapping(value = "/getConsultContent", method = RequestMethod.GET)
    //todo resultformatter
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String getConsultContent(Long cnslId) throws GeneralException {
        if(cnslId == null){
            throw new GeneralException("124101");
        }
        TKmConsultApply consultContent = kmConsultSV.getConsultContent(cnslId);
        if(consultContent == null){
            throw new GeneralException("124108");
        }
        HashMap result = new HashMap();

        result.put("data",consultContent);
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 删除咨询表
     *
     * @param cnslId 咨询表id
     * @return 删除结果
     * @throws GeneralException
     */
    @RequestMapping(value = "/deleteConsult", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void deleteConsult(Long cnslId) throws GeneralException{
        if(cnslId == null){
            throw new GeneralException("124104");
        }

        kmConsultSV.deleteConsult(cnslId, Constants.JWCONSULT_TEST_DATA.TEST_REGN_ID);
        /*consultComponent.deleteConsultIndex(cnslId.toString());*/
    }

    /**
     * 校验咨询表名称
     *
     * @param cnslId 咨询表id
     * @param cnslNm 咨询表名称
     * @return
     * @throws GeneralException
     */
    @RequestMapping(value = "/checkConsultNm", method = RequestMethod.GET)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String checkConsultNm(Long cnslId, String cnslNm) throws GeneralException{
        HashMap result = new HashMap();
        if(StringUtil.isEmpty(cnslNm)){
            result.put("object",false);
        }else{
            boolean b = kmConsultSV.checkConsultNm(cnslId, cnslNm, "0");
            result.put("object",b);
        }

        return result.toString();
    }

    public List<TKmConsultApply> formatData(List<LinkedHashMap<String, Object>> list){
        List<TKmConsultApply> resultList = new ArrayList<>();

        TKmConsultApply consultApply;
        for(LinkedHashMap<String, Object> map : list){
            consultApply = new TKmConsultApply();

            consultApply.setModfTime((Timestamp) map.get("modf_time"));
            consultApply.setCnslNm((String)map.get("consultName"));
            //consultApply.setCnslId((Long) map.get("consultId"));
            consultApply.setCnslId(Long.parseLong((String)map.get("consultId")));
            consultApply.setTmpltId(Long.parseLong((String)map.get("templateId")));
            resultList.add(consultApply);
        }

        return resultList;
    }

    /**
     * 咨询表模板下载
     * @param cnsultTmpltId 咨询表模板id
     * @return 模板下载
     * @throws GeneralException
     */
    @RequestMapping(value = "/downldConsult")
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void downldConsult(String cnsultTmpltId, HttpServletResponse response) throws GeneralException {
       Long tmpltId = Long.parseLong(cnsultTmpltId);
       List consultKeysLists= kmConsultTmpltSV.listTKmConsultKeys(tmpltId);
       TKmConsultTmplt kmConsultTmplt=kmConsultTmpltSV.getConsultTmplt(tmpltId);
       if(kmConsultTmplt==null){
           throw new GeneralException("114108");
       }
       String tmpltNm=kmConsultTmplt.getTmpltNm();
       try {
           //输出的文件地址及名称
           response.setHeader("Content-Type", "application/octet-stream");
           response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(tmpltNm + "模板下载.xlsx") + "\"");
           OutputStream out = response.getOutputStream();
           StringBuilder titleStr=new StringBuilder();
           for(int i=0;i<consultKeysLists.size();i++){
               TKmConsultKeys keys=(TKmConsultKeys)consultKeysLists.get(i);
               String colmNm=keys.getColmNm();
               //判断字段是否必填项
               if(String.valueOf(keys.getRequiredFlag()).equals(Constants.NGKM_CONSULT_ISREQUIRED.REQUIRED)){
                   colmNm="(必填)"+colmNm;
               }
               if(i==consultKeysLists.size()-1){
                   titleStr.append(colmNm);
               }else{
                   titleStr.append(colmNm+"|");
               }
           }
           String[] titleRows=titleStr.toString().split("\\|");
           XSSFWorkbook workbook = new XSSFWorkbook();
           this.exportExcel(workbook, 0, tmpltNm, titleRows, null);
           //关闭输入流
           workbook.write(out);
           out.close();
       }catch (IOException e){
           LOGGER.error("下载模板失败：", e.getMessage(),e);
       }
    }
    /**
     * @Author:duft
     * @Date: 2017/12/12  21:12
     * @param workbook，工作表
     * @param sheetNum，sheet的位置，0表示第一个表格中的第一个sheet
     * @param sheetTitle，sheet工作簿的名称，如sheet1是"汇总"，sheet2是"流水"
     * @param headers 表格里面的标题
     * @param result    表格里需要的数据
     * @Description: 下载execl模板
     */
    public void exportExcel(XSSFWorkbook workbook, int sheetNum, String sheetTitle, String[] headers, List<List<String>> result) {
        // 第一步，创建一个webbook，对应一个Excel以xsl为扩展名文件
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        //第二步， 生成表格第一行的样式和字体
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setLocked(false);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        //设置字体所在的行高度
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(false);
        // 产生表格标题行
        int rowNum = 0;
        XSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
            //设置列宽度大小
            sheet.setColumnWidth(i, 15 * 256);
        }
    }

}
