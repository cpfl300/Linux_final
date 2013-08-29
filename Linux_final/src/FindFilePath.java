import java.io.File;

//경로1과 파일이름2를 입력받아서, 경로1 디렉토리에 파일2 가 존재하면 
//파일2의 전체 경로를 출력해 주는 프로그램

public class FindFilePath {

	private void findPath(String dPath, String fileName) {
		boolean isContain = false;
		File targetFile = new File(dPath);
		
		if(!targetFile.isDirectory()) {
			System.out.println("Is not Directory..");
			System.exit(1);
		}
		
		String[] fileList = targetFile.list();
		
		for(String FilesName : fileList){		
			if (FilesName.equals(fileName)) isContain = true;
			else{
				System.out.println("This directory not have this file");
				System.exit(1);
			}
		}
		
		if (isContain == true){
			System.out.println("Path : " + targetFile.getAbsolutePath()+"/"+fileName);
		}
		
	}

	public static void main(String[] args) {
		FindFilePath ffp = new FindFilePath();
		ffp.findPath("files/contains", "thisFile.txt");
	}
	
	
}
