public class StdinTest
{
    public static void main(String args[])
    {
        Stdin si = new Stdin();

        System.out.print("stringlen = ");
        String str = si.gets();
        System.out.println(str);

        System.out.print("seisuu = ");
        int no = si.geti();
        System.out.println(no);

        System.out.print("zissuu = ");
        double po = si.getd();
        System.out.println(po);
    }
}