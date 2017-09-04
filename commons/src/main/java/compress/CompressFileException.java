package compress;

/**
 * 文件压缩例外。
 * Created by hOward on 2015/8/22.
 */
public class CompressFileException extends Exception {
  public CompressFileException(Exception e) {
    super(e);
  }

  public CompressFileException(String message) {
    super(message);
  }

  public CompressFileException(String message, Exception e) {
    super(message, e);
  }
}
