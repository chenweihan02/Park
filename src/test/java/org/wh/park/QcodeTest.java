package org.wh.park;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.jupiter.api.Test;

/**
 * @author ChenWeihan
 * @description TODO
 */
public class QcodeTest {

    @Test
    void create() {
        QrConfig config = new QrConfig(600, 600);
        //L M Q H 低到高
        config.setErrorCorrection(ErrorCorrectionLevel.L);
        QrCodeUtil.generate("baidu.com", config, FileUtil.file("d:/qcore.jpg"));
    }
}
