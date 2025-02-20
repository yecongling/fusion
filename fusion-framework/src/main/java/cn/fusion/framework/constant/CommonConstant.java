package cn.fusion.framework.constant;

/**
 * @ClassName CommonConstant
 * @Description 通用常量定义
 * @Author ycl
 * @Date 2024/11/4 17:59
 * @Version 1.0
 */
public interface CommonConstant {
    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG_1 = 1;

    /**
     * 未删除
     */
    Integer DEL_FLAG_0 = 0;

    /**
     * 系统日志类型： 登录
     */
    int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    int LOG_TYPE_2 = 2;

    /**
     * 操作日志类型： 查询
     */
    int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 倒入
     */
    int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    int OPERATE_TYPE_6 = 6;

    /**
     * 登录用户Token令牌缓存KEY前缀
     */
    String PREFIX_USER_TOKEN = "prefix_user_token_";

    // 用户token前缀
    String USER_TOKEN_PREFIX = "user_token_";

    /**
     * 登录二维码
     */
    String LOGIN_QRCODE_PRE = "QRCODELOGIN:";
    String LOGIN_QRCODE = "LQ:";
    /**
     * 登录二维码token
     */
    String LOGIN_QRCODE_TOKEN = "LQT:";

    // 登录操作
    String LOGIN_OPERATION = "login";

    /**
     * 登录失败前缀
     */
    String LOGIN_FAIL = "login_fail:";

    /**
     * 0：一级菜单
     */
    Integer MENU_TYPE_0 = 0;
    /**
     * 1：子菜单
     */
    Integer MENU_TYPE_1 = 1;
    /**
     * 2：子路由
     */
    Integer MENU_TYPE_2 = 2;

    /* 3：按钮权限 */
    Integer MENU_TYPE_3 = 3;

    /**
     * 通告对象类型（USER:指定用户，ALL:全体用户）
     */
    String MSG_TYPE_USER = "USER";
    String MSG_TYPE_ALL = "ALL";

    /**
     * 发布状态（0未发布，1已发布，2已撤销）
     */
    String NO_SEND = "0";
    String HAS_SEND = "1";
    String HAS_CANCEL = "2";

    /**
     * 阅读状态（0未读，1已读）
     */
    String HAS_READ_FLAG = "1";
    String NO_READ_FLAG = "0";

    /**
     * 优先级（L低，M中，H高）
     */
    String PRIORITY_L = "L";
    String PRIORITY_M = "M";
    String PRIORITY_H = "H";

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    String SMS_TPL_TYPE_0 = "0";
    String SMS_TPL_TYPE_1 = "1";
    String SMS_TPL_TYPE_2 = "2";

    /**
     * 状态(0无效1有效)
     */
    String STATUS_0 = "0";
    String STATUS_1 = "1";


    /**
     * 消息类型1:通知公告2:系统消息
     */
    String MSG_CATEGORY_1 = "1";
    String MSG_CATEGORY_2 = "2";

    /**
     * 是否配置菜单的数据权限 1是0否
     */
    Integer RULE_FLAG_0 = 0;
    Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1正常(解冻) 2冻结
     */
    Integer USER_UNFREEZE = 1;
    Integer USER_FREEZE = 2;

    /**
     * 字典翻译文本后缀
     */
    String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * online参数值设置（是：Y, 否：N）
     */
    String ONLINE_PARAM_VAL_IS_TURE = "Y";
    String ONLINE_PARAM_VAL_IS_FALSE = "N";

    /**
     * 文件上传类型（本地：local，Minio：minio）
     */
    String UPLOAD_TYPE_LOCAL = "local";
    String UPLOAD_TYPE_MINIO = "minio";

    /**
     * 文档上传自定义桶名称
     */
    String UPLOAD_CUSTOM_BUCKET = "eoafile";
    /**
     * 文档上传自定义路径
     */
    String UPLOAD_CUSTOM_PATH = "eoafile";
    /**
     * 文件外链接有效天数
     */
    Integer UPLOAD_EFFECTIVE_DAYS = 1;

    /**
     * 系统通告消息状态：0=未发布
     */
    String ANNOUNCEMENT_SEND_STATUS_0 = "0";
    /**
     * 系统通告消息状态：1=已发布
     */
    String ANNOUNCEMENT_SEND_STATUS_1 = "1";
    /**
     * 系统通告消息状态：2=已撤销
     */
    String ANNOUNCEMENT_SEND_STATUS_2 = "2";

    /**
     * ONLINE 报表权限用 从request中获取地址栏后的参数
     */
    String ONL_REP_URL_PARAM_STR = "onlRepUrlParamStr";

    /**
     * POST请求
     */
    String HTTP_POST = "POST";

    /**
     * PUT请求
     */
    String HTTP_PUT = "PUT";

    /**
     * PATCH请求
     */
    String HTTP_PATCH = "PATCH";

    /**
     * 未知的
     */
    String UNKNOWN = "unknown";

    /**
     * 字符串http
     */
    String STR_HTTP = "http";

    /**
     * String 类型的空值
     */
    String STRING_NULL = "null";

    /**
     * http:// http协议
     */
    String HTTP_PROTOCOL = "http://";

    /**
     * https:// https协议
     */
    String HTTPS_PROTOCOL = "https://";

}
