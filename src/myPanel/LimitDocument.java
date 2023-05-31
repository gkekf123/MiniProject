package myPanel;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
// 텍스트 필드의 글자 수 제한
class LimitDocument extends PlainDocument{
	private int limit;
	
	public LimitDocument(int limit) {
		this.limit = limit;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null) {
			return;
		}
		
		if((getLength() + str.length()) <= limit) {
			super.insertString(offs, str, a);
		}
	}
	
}
