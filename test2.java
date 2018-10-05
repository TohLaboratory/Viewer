public class test2  {
    public static void main(String []  args)  {
	String [] str = "  1  34  1 4 90  3   5".split(" ");
	System.out.println("Size = " + str.length);
        for (int i = 0 ; i < str.length ; ++i)  {
	    if (str[i].isEmpty() || str[i].charAt(0) == ' ')  continue;
	    System.out.println(str[i]);
	}
    }
}
