import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// (1) 파일경로 1과 파일경로2를 입력받아서 1을 2에 복사하는 프로그램.
//디렉토리일 경우 복사할수 없다는 메시지를 발생하고 종료.

public class FileCopy {

	private void copy(String origin, String copy) throws IOException {

		File source = new File(origin);		
		File target = new File(copy);		
		
		if(source.isDirectory()){
			System.out.println("디렉터리는 복사할 수 없습니다!");
			System.exit(1);
		}
		
		
		FileWriter fw = new FileWriter(target) ;//FileWriter(copy);
		BufferedWriter bw = new BufferedWriter(fw);
		
		FileReader fr = new FileReader(origin);
		BufferedReader br = new BufferedReader(fr);
		
		String data = br.readLine();
		while(data != null){
			bw.write(data + "\n");
			data = br.readLine();
		}
		
		System.out.println("complete!");
		
		File targetFile = new File(copy);
		System.out.println("copyed path: "+targetFile.getAbsolutePath());
		System.out.println("copyed name: "+targetFile.getName());
		
		bw.close();
		fw.close();
		br.close();
		fr.close();
		
		FileReader fr2 = new FileReader(target);
		int test = fr2.read();
		while(test != -1){
			System.out.print((char)test);
			test = fr2.read();
		}
		
	}

	public static void main(String[] args) throws IOException {
		FileCopy fc = new FileCopy();
		fc.copy("files/source.txt", "files/target.txt");
	}

}
