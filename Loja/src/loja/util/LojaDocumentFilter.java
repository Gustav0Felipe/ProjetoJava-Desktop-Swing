package loja.util;

import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LojaDocumentFilter {

    public LojaDocumentFilter() {
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setSize(300, 300);
        frame.add(createFilteredFieldDouble());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    public static JTextField createFilteredFieldLimit(int max) {
    	  JTextField field = new JTextField();
          AbstractDocument document = (AbstractDocument) field.getDocument();
          final int maxCharacters = max;
          document.setDocumentFilter(new DocumentFilter() {
              public void replace(FilterBypass fb, int offs, int length,
                      String str, AttributeSet a) throws BadLocationException {
                 
                  if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                          ) {
                      super.replace(fb, offs, length, str, a);
                  } else {
                      Toolkit.getDefaultToolkit().beep();
                  }
              }

              public void insertString(FilterBypass fb, int offs, String str,
                      AttributeSet a) throws BadLocationException {

                  if ((fb.getDocument().getLength() + str.length()) <= maxCharacters) {
                      super.insertString(fb, offs, str, a);
                  } else {
                      Toolkit.getDefaultToolkit().beep();
                  }
              }
          });
          return field;
	}
    
    public static JPasswordField createPassWordFieldLimit(int max) {
    	JPasswordField field = new JPasswordField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = max;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                    String str, AttributeSet a) throws BadLocationException {
               
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        ) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                    AttributeSet a) throws BadLocationException {

                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
	}

	public static JTextField createFilteredFieldDouble() {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = 10;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                    String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                    AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
    }
    
    public static JTextField createFilteredFieldInteger() {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = 10;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                    String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+?[0-9]{0}$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                    AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+?[0-9]{0}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
    }
    
    public static JTextField createFilteredFieldString(int max) {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = max;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                    String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("[a-zA-Z]+")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                    AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("[a-zA-Z]+)")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LojaDocumentFilter();
            }
        });
    }
}