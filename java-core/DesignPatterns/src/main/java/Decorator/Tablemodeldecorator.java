package Decorator;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

// TableModelDecorator implements TableModelListener. That
// listener interface defines one method: tableChanged(), which
// is called when the table model is changed. That method is
// not implemented in this abstract class; it's left for
// subclasses to implement.
public abstract class Tablemodeldecorator
        implements TableModel, TableModelListener {
  private TableModel realModel; // We're decorating this model

  public Tablemodeldecorator(TableModel model) {
    this.realModel = model;
    realModel.addTableModelListener(this);
  }

  // The following 9 methods are defined by the TableModel
  // interface; all of those methods forward to the real model.
  public void addTableModelListener(TableModelListener l) {
    realModel.addTableModelListener(l);
  }

  public Class getColumnClass(int columnIndex) {
    return realModel.getColumnClass(columnIndex);
  }

  public int getColumnCount() {
    return realModel.getColumnCount();
  }

  public String getColumnName(int columnIndex) {
    return realModel.getColumnName(columnIndex);
  }

  public int getRowCount() {
    return realModel.getRowCount();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return realModel.getValueAt(rowIndex, columnIndex);
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return realModel.isCellEditable(rowIndex, columnIndex);
  }

  public void removeTableModelListener(TableModelListener l) {
    realModel.removeTableModelListener(l);
  }

  public void setValueAt(Object aValue,
                         int rowIndex, int columnIndex) {
    realModel.setValueAt(aValue, rowIndex, columnIndex);
  }

  // The getRealModel method is used by subclasses to
  // access the real model.
  protected TableModel getRealModel() {
    return realModel;
  }
}
