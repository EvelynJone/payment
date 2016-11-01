package cn.itcast.payment.servlet;

import cn.itcast.payment.util.ConfigInfo;
import cn.itcast.payment.util.PanymentUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 发起支付请求
 * Created by Administrator on 2016/11/1.
 */
public class PaymentRequest  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gbk");
        String orderid = req.getParameter("orderid");//订单号
        String amount = req.getParameter("amount");//支付金额
        String pd_FrpId = req.getParameter("pd_FrpId");//选择的支付银行

        String p1_MerId = ConfigInfo.getValue("p1_MerId");
        String keyValue = ConfigInfo.getValue("keyValue");
        String merchantCallbackURL = ConfigInfo.getValue("merchantCallbackURL");

        String messageType = "Buy"; // 请求命令,在线支付固定为Buy
        String currency = "CNY"; // 货币单位
        String productDesc = ""; // 商品描述
        String productCat = ""; // 商品种类
        String productId = ""; // 商品ID
        String addressFlag = "0"; // 需要填写送货信息 0：不需要 1:需要
        String sMctProperties = ""; // 商家扩展信息
        String pr_NeedResponse = "0"; // 应答机制
        String md5hmac = PanymentUtil.buildHmac(messageType, p1_MerId, orderid, amount, currency,
                productId, productCat, productDesc, merchantCallbackURL, addressFlag, sMctProperties,
                pd_FrpId, pr_NeedResponse, keyValue);

        req.setAttribute("messageType", messageType);
        req.setAttribute("merchantID", p1_MerId);
        req.setAttribute("orderId", orderid);
        req.setAttribute("amount", amount);
        req.setAttribute("currency", currency);
        req.setAttribute("productId", productId);
        req.setAttribute("productCat", productCat);
        req.setAttribute("productDesc", productDesc);
        req.setAttribute("merchantCallbackURL", merchantCallbackURL);
        req.setAttribute("addressFlag", addressFlag);
        req.setAttribute("sMctProperties", sMctProperties);
        req.setAttribute("frpId", pd_FrpId);
        req.setAttribute("pr_NeedResponse", pr_NeedResponse);
        req.setAttribute("hmac", md5hmac);

        req.getRequestDispatcher("/WEB-INF/page/connection.jsp").forward(req, resp);

    }
}
