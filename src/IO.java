
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class IO {
	
	public void writeExercise(String context, int flag) {
		File exerFile = new File("D:\\test\\Exercise.txt");
		File answerFile = new File("D:\\test\\Answer.txt");
		if (flag == 0) {
			writeIn(context, exerFile);
		} else {
			writeIn(context, answerFile);
		}
	}
	
	private void writeIn(String context, File file) {
		FileWriter fileWriter;
		BufferedWriter brout;
		try {
			fileWriter = new FileWriter(file, true);
			brout = new BufferedWriter(fileWriter);
			brout.write(context);
			brout.flush();
			brout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
