import java.util.ArrayList;

import javax.naming.InitialContext;

public class Score {
	int up;
	int down;
	boolean canBeUse = true; // 算式是否可用
	
	public Score() {
		// TODO Auto-generated constructor stub
	}
	
	public void changeScore(String s) {
		boolean isBig = false;
		boolean isHave = false;
		ArrayList<String> list = new ArrayList<>();
		StringBuilder temp = new StringBuilder();
		
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c == '/')
				isHave = true;
			if (c == '\'')
				isBig = true;
			if (c >= '0' && c <= '9') {
				temp.append(c);
			} else {
				list.add(temp.toString());
				temp = new StringBuilder();
			}
			if (i == s.length() - 1) {
				if (!temp.toString().isEmpty()) {
					list.add(temp.toString());
				}
			}
		}
		
		if (list.isEmpty()) {
			canBeUse = false;
		} else {
			if (!isBig && !isHave) {
				this.up = Integer.parseInt(list.get(0));
				this.down = 1;
			} else if (isBig) {
				this.up = Integer.parseInt(list.get(0))
						* Integer.parseInt(list.get(2)) + Integer.parseInt(list.get(1));
				this.down = Integer.parseInt(list.get(2));
			} else if (isHave && !isBig){
				this.up = Integer.parseInt(list.get(0));
				this.down = Integer.parseInt(list.get(1));
			}
		}
	}
	
}
