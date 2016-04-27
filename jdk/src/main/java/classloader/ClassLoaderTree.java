package classloader;

public class ClassLoaderTree {

	public static void main(String[] args) {
		ClassLoader loader = ClassLoaderTree.class.getClassLoader();
		printCLTree(loader);
	}

	public static void printCLTree(ClassLoader loader){
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
	}
}
