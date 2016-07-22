package jdk.me.compress;

/**
 * 基于file system的压缩接口
 * Created by hOward on 2015/8/22.
 */
public interface CompressFile {

    /**
     * 压缩方法
     * @param compressPath 压缩文件
     * @param sourcePath 待压缩文件路径数组
     * @throws CompressFileException
     */
    void compress(String compressPath, String[] sourcePath) throws CompressFileException;
}
