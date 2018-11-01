package hadoop.DAO;

public interface HadoopDAO {

	/**
	 * @author foton
	 * @return
	 * @desc 创建目录 
	 */
	
	public boolean createDirtion(String pathName);
	
	/**
	 * @author foton
	 * @desc 删除目录
	 */
	
	public boolean deleteDirtion(String pathName);
	
	/**
	 * @author foton
	 * @desc 查看目录
	 */
	
	public boolean showDirtionList(String pathName);
	
	/**
	 * @author foton
	 * @desc 从本地上传文件
	 */
	
	public boolean putFromLocal(String pathName);
	
	/**
	 * @author foton
	 * @desc 从HDFS下载文件
	 */
	
	public boolean getFromHDFS(String pathName);
	
	
}
