package helper;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedCharDocument extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit = 10;
	
	public LimitedCharDocument(int limit) {
		this.limit = limit;
	}
	public LimitedCharDocument() {
	}

	@Override
	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		String newString = str;
		if (str != null) {
			int currentLength = this.getLength();
			int newTextLength = str.length();
			if (currentLength + newTextLength > limit) {
				newString = str.substring(0, limit - currentLength);
			}
		}

		super.insertString(offset, newString, a);
	}
}