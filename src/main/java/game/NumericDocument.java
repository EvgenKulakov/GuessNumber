package game;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumericDocument extends PlainDocument {

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

        if (getLength() + str.length() > 3) {
            return;
        }

        if (!str.matches("\\d")) {
            return;
        }

        super.insertString(offs, str, a);
    }
}
