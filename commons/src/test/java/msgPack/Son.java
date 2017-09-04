package msgPack;

import org.msgpack.annotation.Index;

public class Son {

  /**
   * 编号
   */
  @Index(1)
  public Integer id;
  /**
   * 名字
   */
  @Index(2)
  public String name;
  /**
   * 身高
   */
  @Index(3)
  public Double height;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

}
