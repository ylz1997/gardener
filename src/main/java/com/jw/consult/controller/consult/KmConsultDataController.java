package com.jw.consult.controller.consult;

import com.github.pagehelper.StringUtil;
import com.jw.base.BaseFormateter;
import com.jw.base.Constants;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.consult.bean.*;
import com.jw.consult.controller.aop.ResultFormatter;
import com.jw.consult.service.IKmConsultDataSV;
import com.jw.consult.service.IKmConsultSV;
import com.jw.consult.service.IKmConsultTmpltSV;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 咨询表记录表操作
 *
 * @author ylz1997 2018-3-1 20:18:15
 */
@RestController
@RequestMapping("/consultData")
public class KmConsultDataController {

    private static final Logger logger = LoggerFactory.getLogger(KmConsultDataController.class);

/*    @Autowired
    private UserComponent userComponent;*/

    @Autowired
    private IKmConsultDataSV iKmConsultDataSV;

/*    @Autowired
    private ConsultDataComponent consultDataComponent;*/

    @Autowired
    private IKmConsultTmpltSV kmConsultTmpltSV;
    @Autowired
    private IKmConsultSV iKmConsultSV;
/*    @Autowired
    private IKmDocEditPusSV editPusSV;*/

    /**
     * 查询咨询表记录列表
     *
     * @return
    */
    @RequestMapping(value = "/getConsultList", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String getConsultDataList(@RequestBody ConsultSearchVO searchVO) throws GeneralException {
        HashMap result = new HashMap();
        if(searchVO == null){
            logger.error("searchVO is null!");
            return null;
        }
        ConsultDataVO consultDataVO = iKmConsultDataSV.getConsultApply(searchVO);


        result.put("data",consultDataVO.getListConsultRowResult());
        result.put("recordTotal",consultDataVO.getTotal());
        result.put("recordsFiltered",consultDataVO.getTotal());
        result.put("draw", searchVO.getDraw());

        return JsonUtil.convertObject2Json(result);
    }

/*    *//**
     * 查询咨询表记录列表-前台
     *
     * @return
     *//*
    @RequestMapping(value = "/getConsultListForPortal", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public Result getConsultDataListForPortal(String param, Long cnslId, Integer page, Integer limit) throws GeneralException{
        Result result = new Result();
        CsfInputObject inputObject = new CsfInputObject();
        Map<String, Object> params = inputObject.getParams();

        Integer newPage = page;
        if(page == null){
            newPage = 1;
        }

        Integer newLimit = limit;
        if(newLimit == null){
            newLimit = 10;
        }

        params.put("indexType","consult_message");
        params.put("page", newPage);
        params.put("size", newLimit);
        params.put("indexName","ngkm.consult_message");
        params.put("sortField", "modeTime=DESC");

        List<Map<String, Object>> paramList = null;
        if(StringUtil.isNotEmpty(param)){
            paramList = (List<Map<String, Object>>)JSONUtils.parse(param);
        }

        StringBuilder sb = new StringBuilder();

        if(paramList != null){
            for(Map<String, Object> map : paramList){
                if(StringUtil.isNotEmpty((String) map.get("column")) && ((String)map.get("column")).startsWith("consult_date")){
                    String value = (String) map.get("value");
                    String[] values = value.split(",");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateStr0="null";
                    String dateStr1="null";
                    if (!"null".equals(values[0])){
                        try{
                            Date date = format.parse(values[0]);
                            dateStr0 = String.valueOf(date.getTime());
                        }catch(Exception e){
                            LOGGER.error("日期类型格式转化异常", e);
                            continue;
                        }
                    }
                    if (!"null".equals(values[1])){
                        try{
                            Date date = format.parse(values[1]);
                            dateStr1 = String.valueOf(date.getTime());
                        }catch(Exception e){
                            LOGGER.error("日期类型格式转化异常", e);
                            continue;
                        }
                    }
                    map.put("value",dateStr0+","+dateStr1);
                }
                sb.append(map.get("column")).append("=1").append(",");
            }
        }else{
            paramList = new ArrayList<>();
        }
        sb.append("consultId=1");
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("column", "consultId");
        tempMap.put("type", "any,array");
        tempMap.put("value", cnslId);
        paramList.add(tempMap);

        params.put("searchInfo", sb.toString());
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
        List<Map<String, Object>> document = null;
        if(total != 0){
            document = formatData((List<LinkedHashMap<String, Object>>) parse1.get("document"));
        }
        result.setBeans(document);
        return result;
    }*/

    /**
     * 查询单个咨询表记录的信息
     *
     * @return
     * @throws GeneralException
     */
    @RequestMapping(value = "/getConsultData", method = RequestMethod.GET)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String getConsultData(Long recId) throws GeneralException{
        if(recId == null){
            throw new GeneralException("124112");
        }

        List<TKmConsultData> consultData = iKmConsultDataSV.
                getConsultData(recId, "000");
        if(consultData == null || consultData.isEmpty()){
            throw new GeneralException("124113");
        }
        HashMap result = new HashMap();

        result.put("beans",consultData);
        return result.toString();
    }

    /**
     * 新增或更新记录
     *
     * @throws GeneralException
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/addOrUpdateConsultData", method = RequestMethod.POST)
    public String addOrUpdateConsultData(String param, Long tmpltId, Long cnslId, String recIds) throws GeneralException{
        if(StringUtil.isEmpty(param)){
            throw new GeneralException("124109");
        }
        if(tmpltId == null || cnslId == null){
            throw new GeneralException("124111");
        }
        Object o = JsonUtil.convertJson2Object(param, Object.class);

        if(o == null){
            throw new GeneralException("124110");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("data", o);
        map.put("staffCode", Constants.JWCONSULT_TEST_DATA.TEST_USER);
        map.put("regnId", Constants.JWCONSULT_TEST_DATA.TEST_REGN_ID);
        map.put("tenantId", Constants.JWCONSULT_TEST_DATA.TEST_TEND_ID);
        map.put("tmpltId", tmpltId);
        map.put("cnslId", cnslId);
        map.put("recIds", recIds);

        final Long id = iKmConsultDataSV.addConsultData(map);
        HashMap result = new HashMap();
        result.put("returnCode",0);
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 咨询表记录批量修改
     *
     * @param param
     * @param tmpltId
     * @param cnslId
     * @param recIds
     * @throws GeneralException
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/batchUpdateConsultData", method = RequestMethod.POST)
    public void batchUpdateConsultData(String param, Long tmpltId, Long cnslId, final String recIds) throws GeneralException{
        if(StringUtil.isEmpty(param)){
            throw new GeneralException("124109");
        }
        if(tmpltId == null || cnslId == null){
            throw new GeneralException("124111");
        }
        Object o = JsonUtil.convertJson2Object(param, Object.class);

        if(o == null){
            throw new GeneralException("124110");
        }

        List<Long> longs = iKmConsultDataSV.checkDelete(recIds, "0");
        if(longs != null && !longs.isEmpty()){
            throw new GeneralException("124114");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("data", o);
       /* map.put("staffCode", userComponent.getUserInfoSession().getStaffCode());
        map.put("regnId", userComponent.getUserInfoSession().getProvnce());
        map.put("tenantId", userComponent.getUserInfoSession().getTenantId());
*/
        map.put("tmpltId", tmpltId);
        map.put("cnslId", cnslId);
        map.put("recIds", recIds);

        iKmConsultDataSV.batchUpdateConsultData(map);

/*        consultDataComponent.creatConsultIndex(recIds);*/
    }

    /**
     * 删除/更新 前校验
     *
     * @param recIds
     * @return
     * @throws GeneralException
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/checkDelete", method = RequestMethod.GET)
    public String checkDelete(String recIds) throws GeneralException{
        List<Long> longs = iKmConsultDataSV.checkDelete(recIds, "0");
        HashMap result = new HashMap();
        if(longs != null && !longs.isEmpty()){
            result.put("beans",longs);
        }
        return result.toString();
    }

    /**
     * 删除咨询表记录内容
     *
     * @param recIds
     * @throws GeneralException
     */
    @RequestMapping(value = "/deleteConsultData", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void deleteConsultData(final String recIds) throws GeneralException{
        if(StringUtil.isEmpty(recIds)){
            throw new GeneralException("124112");
        }

        String[] arr = recIds.split(",");
        List<String> list = Arrays.asList(arr);
        iKmConsultDataSV.deleteConsultData(list, "0");

        /*consultDataComponent.deleteConsultIndex(recIds);*/
    }

    private List<Map<String, Object>> formatData(List<LinkedHashMap<String, Object>> list){
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map temp;
        for(LinkedHashMap<String, Object> map : list){
            temp = new HashMap();
            for(Map.Entry<String, Object> entry: map.entrySet()){
                if(entry.getKey().indexOf("columnId_") == 0){
                    String value = (String) map.get("keyValue_" + entry.getValue());
                    if(StringUtil.isEmpty(value)){
                        value = (String) map.get("consult_date_" + entry.getValue());
                        if("null".equals(value) || StringUtil.isEmpty(value)){
                            value = (String) map.get("consult_num_" + entry.getValue());
                        }
                    }

                    if("null".equals(value)){
                        value = "";
                    }

                    temp.put(entry.getValue(), value);
                }else if("recordId".equals(entry.getKey())){
                    temp.put("recId", entry.getValue());
                }
            }
            temp.put("modeTime", map.get("modeTime"));
            resultList.add(temp);
        }
        return resultList;
    }



    /**
     * @Description:咨询表记录导入数据入表
     * @Author:duft
     * @param tmpltId 模板id
     * @return Result
     * @throws GeneralException
     */
    @RequestMapping(value = {"/iportConsultRecord"}, method = {RequestMethod.POST})
    public String importRecordUpload(String knwlgId, String dataArrays, String tmpltId, String cnslId) throws GeneralException {
        String comId="";//关联知识列ID
        HashMap result = new HashMap();

        List consultKeysLists= kmConsultTmpltSV.listTKmConsultKeys(Long.parseLong(tmpltId));
        for(int j=0;j<consultKeysLists.size();j++){
            TKmConsultKeys consultKeys=(TKmConsultKeys)consultKeysLists.get(j);
            //关联知识列
            if("rel".equals(consultKeys.getColmTypeCd())){
                comId=consultKeys.getColmId().toString();
                continue;
            }
        }
        //如果导入表格中没有关联知识列，但当前关联知识ID不为空，则直接返回提示
        if("".equals(comId) && knwlgId!=null){
            result.put("returnCode","-1");
            result.put("returnMessage","导入的文件中没有关联知识列，不能导入当前关联知识！");
            return result.toString();
        }
        //获取表格数据导入数据
        ArrayList consultList=(ArrayList)JsonUtil.convertJson2Object(dataArrays, Object.class);
        for(int i=0;i<consultList.size();i++){
            HashMap<String,String> conulseMap=(HashMap)consultList.get(i);
            //如果关联知识ID不为空，且导入表格中有关联知识列，则全部替换为当前关联知识ID
            if(knwlgId!=null){
                conulseMap.put(comId,knwlgId);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("data", conulseMap);
/*            map.put("staffCode", userComponent.getUserInfoSession().getStaffCode());
            map.put("regnId", userComponent.getUserInfoSession().getProvnce());
            map.put("tenantId", userComponent.getUserInfoSession().getTenantId());*/
            map.put("tmpltId", Long.parseLong(tmpltId));
            map.put("cnslId", Long.parseLong(cnslId));

            final Long id = iKmConsultDataSV.addConsultData(map);
            /*//刷新索引
            consultDataComponent.creatConsultIndex(id.toString());*/
        }
        result.put("rturnCode","0");
        result.put("returnMessage","数据正在导入中，请稍后点击查询查看结果。");
        return  result.toString();
    }

    /**
     * @Description:咨询表记录导入
     * @Author:duft
     * @param tmpltId 模板id
     * @return Result
     * @throws GeneralException
     */
    @RequestMapping(value = {"/fileUpload"}, method = {RequestMethod.POST}, consumes = {"application/json", "multipart/form-data"}, produces = "text/plain")
    public void consultRecordUpload(HttpServletRequest request, String tmpltId, HttpServletResponse response) throws GeneralException ,IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileupload");
        Map<String,Object> returnMap = new HashMap<>();
        MultipartFile uploadFile = files.get(0);
        InputStream inputStream = null;
        String fileName = uploadFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        Workbook workbook = null;
        try {
            if (".xls".equals(suffix)) {
                inputStream = uploadFile.getInputStream();
                workbook = new HSSFWorkbook(inputStream);
            } else if (".xlsx".equals(suffix)) {
                inputStream = uploadFile.getInputStream();
                workbook = new XSSFWorkbook(inputStream);
            } else {
                returnMap.put("returnCode","-1");
                returnMap.put("returnMessage","文件类型必须为Excel，即以xlsx或xls结尾的文件!");
                response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
                return;
            }
        } catch (IOException e) {
            logger.error("导入失败", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        List consultKeysLists= kmConsultTmpltSV.listTKmConsultKeys(Long.parseLong(tmpltId));
        Sheet sheetData = workbook.getSheetAt(0);
        if (sheetData == null) {
            throw new GeneralException("KMKNOWLEDGBATCHUPLOAD004");
        }

//        CellStyle  style = workbook.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.RED.getIndex());
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        dataCell = rows.createCell((short) 3);
//        dataCell.setCellStyle(style);
        //行数
        int rowsNum = sheetData.getPhysicalNumberOfRows();
        //列数
        int coloumNum = sheetData.getRow(0).getPhysicalNumberOfCells();
        if(coloumNum!=consultKeysLists.size()){
            returnMap.put("returnCode","-1");
            returnMap.put("returnMessage","导入表格与原模板不匹配！");
            response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
            return;
        }
        Cell dataCell;
        String cellValue;
        Row rows;
        TKmConsultKeys consultKeys=null;
        List<HashMap<String,String>> hashMapList=new ArrayList<>();
        for (int i=0;i<rowsNum;i++){
            HashMap<String,String> consultMap= new HashMap<>();
            rows = sheetData.getRow(i);
            for (int j=0;j<coloumNum;j++){
                consultKeys=(TKmConsultKeys)consultKeysLists.get(j);
                String colmNm=consultKeys.getColmNm();
                if(String.valueOf(consultKeys.getRequiredFlag()).equals(Constants.NGKM_CONSULT_ISREQUIRED.REQUIRED)){
                    colmNm="(必填)"+colmNm;
                }
                dataCell = rows.getCell(j);
                if (dataCell == null) {
                    cellValue="";
                }else{
                    cellValue=getCellValue(dataCell);
                }
                int count1=j+1;
                if(i==0 && !colmNm.equals(cellValue)){
                    returnMap.put("returnCode","-1");
                    returnMap.put("returnMessage","导入表格与原模板不匹配！第1行第"+count1+"列");
                    response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
                    return;
                }else{
/*                    //关联知识列,如果为知识ID列，需检验是否正确
                    if("rel".equals(consultKeys.getColmTypeCd()) && i>0){
                        int count=i+1;
                        if(cellValue!=null && !"".equals(cellValue)){
                            if(!StringUtil.isNum(cellValue)||  cellValue.length()>18){
                                returnMap.put("returnCode","-1");
                                returnMap.put("returnMessage","第"+count+"行第"+count1+"列，知识ID不存在！");
                                response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
                                return;
                            }
                            String knlgName= editPusSV.getKnwlgNm(Long.parseLong(cellValue));
                            //该知识不存在
                            if(knlgName==null||"".equals(knlgName)){
                                returnMap.put("returnCode","-1");
                                returnMap.put("returnMessage","第"+count+"行第"+count1+"列，知识ID不存在！");
                                response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
                                return;
                            }
                        }
                    }*/
                    consultMap.put(consultKeys.getColmId().toString(),cellValue);

                }
            }
            //去掉表头数据
            if(i > 0){
                hashMapList.add(consultMap);
                //校验每一行数据
                String returnMsg = iKmConsultDataSV.checkConsultData(consultMap, Long.parseLong(tmpltId), false);
                if(!returnMsg.isEmpty()){
                    int num=i+1;
                    returnMap.put("returnCode","-1");
                    returnMap.put("returnMessage","导入表格第"+num+"行，"+returnMsg);
                    response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
                    return;
                }
            }
        }
        if(hashMapList.isEmpty()){
            returnMap.put("returnCode","-1");
            returnMap.put("returnMessage","导入记录不能为空!");
            response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
            return;
        }
        returnMap.put("returnCode","0");
        returnMap.put("object",hashMapList);
        response.getWriter().print(JsonUtil.convertObject2Json(returnMap).toString());
        return;

    }
    /**
     * 获取Excel单元格数据
     * @param cell
     * @return String
     * @author hufei
     */
    private String getCellValue(Cell cell) {
        String value;
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    } else {
                        value = "";
                    }
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            // 字符串
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            // Boolean
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            // 公式
            case HSSFCell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula() + "";
                break;
            // 空值
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            // 故障
            case HSSFCell.CELL_TYPE_ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
    /**
     * 咨询表记录导出
     * @param cnsultTmpltId 咨询表模板id
     * @return
     * @throws GeneralException
     */
    @RequestMapping(value = "/exportConsult")
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void exportConsultRecord(Long cnsultTmpltId, HttpServletResponse response, String recIds)throws GeneralException{
        List consultKeysLists= kmConsultTmpltSV.listTKmConsultKeys(cnsultTmpltId);
        //记录ID数组
        String[] recIdArr = recIds.split(",");
        String regnId="0";
        try {
            //输出的文件地址及名称
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode( "咨询表记录导出.xlsx") + "\"");
            OutputStream out = response.getOutputStream();
            StringBuilder titleStr=new StringBuilder();
            List<Long> titleColmid = new ArrayList<>();
            Map typeMap= new HashMap();
            //拼接表头
            for(int i=0;i<consultKeysLists.size();i++){
                TKmConsultKeys keys=(TKmConsultKeys)consultKeysLists.get(i);
                String aColmNm=keys.getColmNm();
                if(String.valueOf(keys.getRequiredFlag()).equals(Constants.NGKM_CONSULT_ISREQUIRED.REQUIRED)){
                    aColmNm="(必填)"+aColmNm;
                }
                titleStr.append(aColmNm+"|");
                titleColmid.add(keys.getColmId());
                typeMap.put(keys.getColmId(),keys.getColmTypeCd());
            }
            //导出选择的数据
            Long recId;
            List<List<String>> exportDatas = new ArrayList<>();
            for(int j=0; j<recIdArr.length;j++){
                List<String> data=new ArrayList<>();
                recId= Long.parseLong(recIdArr[j]);
                List<TKmConsultData> consultData = iKmConsultDataSV.getConsultData(recId,regnId);
                if(consultData == null || consultData.isEmpty()) {
                    throw new GeneralException("124109");
                }
                for(Long colmid:titleColmid){
                    boolean flag=true;
                    for(TKmConsultData tKmConsultData:consultData){
                        if(colmid.equals(tKmConsultData.getColmId())){
                            flag=false;
                            String keyVal=tKmConsultData.getKeyval();
                            //日期类型
                            if("date".equals(typeMap.get(colmid))){
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String sd = sdf.format(new Date(Long.parseLong(keyVal)));
                                data.add(sd);
                                break;
                            }if("rel".equals(typeMap.get(colmid)) && !keyVal.isEmpty()){//关联知识
                                data.add(keyVal);
                                break;
                            }else {
                                data.add(keyVal);
                                break;
                            }
                        }
                    }
                    if(flag){
                        data.add("");
                    }
                }
                exportDatas.add(data);
            }
            String[] titleRows=titleStr.toString().split("\\|");
            XSSFWorkbook workbook = new XSSFWorkbook();
            this.exportExcel(workbook, 0, "咨询表记录导出", titleRows, exportDatas);
            //关闭输入流
            workbook.write(out);
            out.close();
        }catch (IOException e){
            logger.info(e.getMessage(),e);
           throw new GeneralException(e.getMessage(),e);
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
        //插入数据
        int index = 1;
        for (List<String> m : result) {
            row = sheet.createRow(index);
            int cellIndex = 0;
            for (String str : m) {
                XSSFCell cell = row.createCell(cellIndex);
                if (StringUtil.isNotEmpty(str)) {
                    cell.setCellValue(str);
                }
                cellIndex++;
            }
            index++;
        }
    }

}
