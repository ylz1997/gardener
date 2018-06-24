require([
    'js/constants/constants',
    'groupSearchForm'
],function(Constants, GroupSearchForm) {

    $(document).ready(function(){
            var grpSearchFormConfig = {
                el: '#groupSearchFormDiv', // * 要绑定的容器
                column: 1, //表单文本框的列数(每一行表单项的个数)，枚举值：2、3、4，默认4。
                advancedQuery: -1, //启用高级查询,items的index(从1开始)大于该数字的item会被隐藏；默认-1不启用
                items: [ // 表单属性信息 及页面显示顺序
                    {
                        element: 'input', //子组件类型（必须）
                        label: '英文和数字', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[A-Za-z0-9]+$ 或 ^[A-Za-z0-9]{4,40}$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '长度为3-20的所有字符', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^.{3,20}$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '由26个英文字母组成的字符串', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[A-Za-z]+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '由数字和26个英文字母组成的字符串', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[A-Za-z0-9]+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '手机', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^0?(13|15|17|18|14)[0-9]{9}$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '电话', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[0-9\\-()（）]{7,18}$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: 'qq', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[1-9]*[1-9][0-9]*$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '字母', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[A-Za-z]+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '小写字母', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[a-z]+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '大写字母', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[A-Z]+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '数字', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^\\d+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '邮编', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^\\d{6}$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '仅中文', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^[\\u4e00-\\u9fa5]+$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '身份证', // 输入框label（必须）
                        config: {
                            attr: {
                                value:"^(^\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input',
                        label: 'IP地址',
                        config: {
                            attr: {
                                value: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input',
                        label: '日期',
                        config: {
                            attr: {
                                value: "^\\\\d{4}(\\\\-|\\\\/|\\\\.)\\\\d{1,2}\\\\1\\\\d{1,2}$",
                                readOnly:"readonly"
                            }
                        }
                    },
                    {
                        element: 'input',
                        label: '时间',
                        config: {
                            attr: {
                                value: "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$",
                                readOnly:"readonly"
                            }
                        }
                    }
                ]
            }
            new GroupSearchForm(grpSearchFormConfig);
    });
})
