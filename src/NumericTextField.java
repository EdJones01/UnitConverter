import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumericTextField extends JTextField {
    public NumericTextField(ActionListener actionListener) {
        super();
        PlainDocument doc = (PlainDocument) getDocument();
        doc.setDocumentFilter(new NumericFilter());
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actionListener.actionPerformed(new ActionEvent(this, 0, "calculate"));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actionListener.actionPerformed(new ActionEvent(this, 0, "calculate"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actionListener.actionPerformed(new ActionEvent(this, 0, "calculate"));
            }
        });
    }

    private class NumericFilter extends DocumentFilter {
        private final String validCharacters = "0123456789.";

        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {
            StringBuilder builder = new StringBuilder(string);
            for (int i = builder.length() - 1; i >= 0; i--) {
                char ch = builder.charAt(i);
                if (validCharacters.indexOf(ch) == -1) {
                    builder.deleteCharAt(i);
                }
            }
            super.insertString(fb, offset, builder.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string,
                            AttributeSet attrs) throws BadLocationException {
            StringBuilder builder = new StringBuilder(string);
            for (int i = builder.length() - 1; i >= 0; i--) {
                char ch = builder.charAt(i);
                if (validCharacters.indexOf(ch) == -1) {
                    builder.deleteCharAt(i);
                }
            }
            super.replace(fb, offset, length, builder.toString(), attrs);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }
}