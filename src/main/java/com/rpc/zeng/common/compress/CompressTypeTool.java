package com.rpc.zeng.common.compress;


//实际调用 通过SPI机制

import com.rpc.zeng.common.compress.bzip.BZipUtils;
import com.rpc.zeng.common.compress.deflater.DeflaterUtils;
import com.rpc.zeng.common.compress.diyzip.DiyZipUtils;
import com.rpc.zeng.common.compress.gzip.GZipUtils;
import com.rpc.zeng.common.compress.lz4.Lz4Utils;
import com.rpc.zeng.common.compress.zip.ZipUtils;
import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class CompressTypeTool implements CompressType {

    private CompressType compressUtils;


    //通过注解获取对应的工具
    public CompressTypeTool(ParameterSettings parameterSettings) {
        switch (parameterSettings.getCompressTool()) {
            case "BZipUtils":
                compressUtils = new BZipUtils();
                break;
            case "DeflaterUtils":
                compressUtils = new DeflaterUtils();
                break;
            case "GZipUtils":
                compressUtils = new GZipUtils();
                break;
            case "Lz4Utils":
                compressUtils = new Lz4Utils();
                break;
            case "ZipUtils":
                compressUtils = new ZipUtils();
                break;
            case "DiyZipUtils":
                compressUtils = new DiyZipUtils();
                break;
            default:
                try {
                    throw new RpcException("兄弟 尚未定义该器件");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                }
        }
    }


    @Override
    public byte[] compress(byte[] bytes) {
        return compressUtils.compress(bytes);
    }

    @Override
    public byte[] deCompress(byte[] bytes) {
        return compressUtils.deCompress(bytes);
    }
}
