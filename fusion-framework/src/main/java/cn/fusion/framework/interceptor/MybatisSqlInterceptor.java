package cn.fusion.framework.interceptor;

/**
 * @ClassName MybatisSqlInterceptor
 * @Description mybatis的SQL执行拦截器，用于监测执行的SQL （后续需要更改，通过前端打开了监控时，这里才进行sql记录并传输到前端-通过netty+websocket）
 * @Author ycl
 * @Date 2024/11/19 11:24
 * @Version 1.0
 */
//@Deprecated
//@Component
//@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
//                Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
//                Object.class, RowBounds.class, ResultHandler.class})})
//public class MybatisSqlInterceptor implements Interceptor {
//    // 日志记录工具
//    private static final Logger logger = LoggerFactory.getLogger(MybatisSqlInterceptor.class);
//
//    @Value("${mybatis.sqlLog:true}")
//    private boolean interceptorEnabled;
//
//    /**
//     * mybatis的执行模块
//     *
//     * @param invocation 执行器
//     * @return 继续执行原有逻辑
//     * @throws Throwable 执行异常
//     */
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        if (!interceptorEnabled) {
//            return invocation.proceed();
//        }
//        JSONObject map = new JSONObject();
//        String msg = "";
//        String type = "";
//        try {
//            // 获取xml中的一个select/update/insert/delete节点，是一条SQL语句
//            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//            Object parameter = null;
//            // 获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
//            if (invocation.getArgs().length > 1) {
//                parameter = invocation.getArgs()[1];
//            }
//            String id = mappedStatement.getId(); // 获取到节点的id,即sql语句的id
//            map.put("id", id);
//            BoundSql boundSql = mappedStatement.getBoundSql(parameter); // BoundSql就是封装myBatis最终产生的sql类
//            Configuration configuration = mappedStatement.getConfiguration(); // 获取节点的配置
//            msg = showSql(configuration, boundSql); // 获取到最终的sql语句
//            type = "sql";
//        } catch (Exception e) {
//            // 如果是发生了异常，将错误信息推送给前台
//            msg = ExceptionUtils.getStackTrace(e);
//            type = "exception";
//        } finally {
//            map.put("msg", msg);
//            map.put("type", type);
//        }
//        logger.info(map.toJSONString());
//        // 这里是需要把执行后的结果进行消息的推送
////        SysOpr sysOpr = servletUtils.getSysOpr();
////        if (sysOpr != null) {
////            pushMsgService.pushMsgToOne(sysOpr.getUserId(), JSONObject.toJSONString(map));
////        }
//        // 执行完上面的任务后，不改变原有的sql执行过程
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    /**
//     * 进行？的替换
//     */
//    public static String showSql(Configuration configuration, BoundSql boundSql) {
//        // 获取参数
//        Object parameterObject = boundSql.getParameterObject();
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        // sql语句中多个空格都用一个空格代替
//        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
//        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
//            // 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
//            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//            // 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
//            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
//                sql = sql.replaceFirst("\\?",
//                        Matcher.quoteReplacement(getParameterValue(parameterObject)));
//            } else {
//                // MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,主要支持对JavaBean、Collection、Map三种类型对象的操作
//                MetaObject metaObject = configuration.newMetaObject(parameterObject);
//                for (ParameterMapping parameterMapping : parameterMappings) {
//                    String propertyName = parameterMapping.getProperty();
//                    if (metaObject.hasGetter(propertyName)) {
//                        Object obj = metaObject.getValue(propertyName);
//                        sql = sql.replaceFirst("\\?",
//                                Matcher.quoteReplacement(getParameterValue(obj)));
//                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
//                        // 该分支是动态sql
//                        Object obj = boundSql.getAdditionalParameter(propertyName);
//                        sql = sql.replaceFirst("\\?",
//                                Matcher.quoteReplacement(getParameterValue(obj)));
//                    } else {
//                        // 未知参数，替换？防止错位
//                        sql = sql.replaceFirst("\\?", "unknown");
//                    }
//                }
//            }
//        }
//        return sql;
//    }
//
//    /**
//     * 如果参数是String，则添加单引号
//     * 如果参数是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
//     */
//    private static String getParameterValue(Object obj) {
//        String value;
//        if (obj instanceof String) {
//            value = "'" + obj + "'";
//        } else if (obj instanceof Date) {
//            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
//                    DateFormat.DEFAULT, Locale.CHINA);
//            value = "'" + formatter.format(new Date()) + "'";
//        } else {
//            if (obj != null) {
//                value = obj.toString();
//            } else {
//                value = "";
//            }
//        }
//        return value;
//    }
//}
