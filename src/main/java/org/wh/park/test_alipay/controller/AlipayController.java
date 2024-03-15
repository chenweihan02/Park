package org.wh.park.test_alipay.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author ChenWeihan
 * @description TODO
 */

@RestController
@Slf4j
@AllArgsConstructor
public class AlipayController {

    private final Config config;

    @GetMapping("pay")
    public String pay() throws Exception {
        Factory.setOptions(config);

        //调用支付宝接口
        AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate("park test 3", "20150320010101003", "23.45");
        //解析结果
        String httpBody = response.getHttpBody();
        JSONObject jsonObject = JSONObject.parseObject(httpBody);
        String qrUrl = jsonObject.getJSONObject("alipay_trade_precreate_response")
                .get("qr_code")
                .toString();

        //生产二维码
        QrCodeUtil.generate(qrUrl, 300, 300, new File("D://桌面//pay.jpg"));
        return httpBody;
    }

    @PostMapping("notify")
    public String notify(HttpServletRequest request) {
        log.info("收到支付成功通知");
        String outTradeNo = request.getParameter("out_trade_no");
        log.info("流水通知{}", outTradeNo);
        return "success";
    }

    @GetMapping("query")
    public String query() throws Exception {
        Factory.setOptions(config);
        AlipayTradeQueryResponse response = Factory.Payment.Common().query("20150320010101003");
        return response.getHttpBody();
    }
}
