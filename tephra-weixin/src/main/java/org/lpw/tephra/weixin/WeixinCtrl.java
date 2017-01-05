package org.lpw.tephra.weixin;

import org.lpw.tephra.ctrl.Forward;
import org.lpw.tephra.ctrl.context.Request;
import org.lpw.tephra.ctrl.execute.Execute;
import org.lpw.tephra.ctrl.template.Templates;
import org.lpw.tephra.ctrl.validate.Validate;
import org.lpw.tephra.util.Message;
import org.lpw.tephra.util.Validator;
import org.lpw.tephra.util.Xml;
import org.lpw.tephra.weixin.gateway.PayGateway;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

/**
 * @author lpw
 */
@Controller("tephra.weixin.ctrl")
@Execute(name = WeixinService.URI, code = "13")
public class WeixinCtrl {
    @Inject
    private Validator validator;
    @Inject
    private Xml xml;
    @Inject
    private Message message;
    @Inject
    private Request request;
    @Inject
    private Forward forward;
    @Inject
    private Templates templates;
    @Inject
    private WeixinHelper weixinHelper;
    @Inject
    private WeixinService weixinService;

    @Execute(name = "wx.+", type = Templates.STRING)
    public Object service() {
        String uri = request.getUri();
        String appId = uri.substring(uri.lastIndexOf('/') + 1);
        String echo = request.get("echostr");
        if (!validator.isEmpty(echo))
            return weixinService.echo(appId, request.get("signature"), request.get("timestamp"), request.get("nonce")) ? echo : "failure";

        String redirect = request.get("redirect");
        if (!validator.isEmpty(redirect)) {
            weixinService.redirect(appId, request.get("code"));
            forward.redirectTo(redirect);

            return null;
        }

        return weixinService.xml(appId, request.getFromInputStream());
    }

    /**
     * 生成预支付参数。
     * type    充值类型，[JSAPI]。
     * mpId    微信公众号AppID。
     * orderNo 订单号。
     * body    订单内容。
     * amount  金额，单位：分。
     * @return 预支付参数。
     */
    @Execute(name = "prepay", validates = {
            @Validate(validator = WeixinService.VALIDATOR_EXISTS_PAY_GATEWAY, parameter = "type", failureCode = 1)
    })
    public Object prepay() {
        String string = weixinService.prepay(request.get("type"), request.get("appId"), request.get("orderNo"), request.get("body"), request.getAsInt("amount"));
        if (string == null)
            return templates.get().failure(1302, message.get("tephra.weixin.prepay.failure"), null, null);

        return string;
    }

    @Execute(name = "jsapi", type = Templates.STRING)
    public Object jsapi() {
        return callback(PayGateway.JSAPI);
    }

    private String callback(String type) {
        weixinHelper.getPayGateway(type).callback(xml.toMap(request.getFromInputStream(), false));

        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
