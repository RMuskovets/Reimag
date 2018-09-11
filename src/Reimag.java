import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Reimag {

    public Reimag() throws IOException {

        try {
            Properties props = new Properties();
            props.load(new FileReader("colors.properties"));
            //int ca = Integer.parseInt(props.getProperty("color1"), 16);
            //int cb = Integer.parseInt(props.getProperty("color2"), 16);
            Color a = Color.decode(props.getProperty("color1"));
            Color b = Color.decode(props.getProperty("color2"));
            Scanner sc = new Scanner(System.in);
            System.out.print("Введіть картинку: ");
            File f = new File(sc.nextLine());
            BufferedImage bi = ImageIO.read(f);
            BufferedImage bi1;
            BufferedImageOp lookup = new LookupOp(new ColorMapper(a, b), null);
            bi1 = lookup.filter(bi, null);
            System.out.print("\n\nПОПЕРЕДЖЕННЯ!!! Програма зберігає у форматі " +
                    "PNG!\n\nВведіть" +
                    " нову назву " +
                    "картинки: ");
            String newn = f.getParent() + File.separator + sc.nextLine();
            ImageIO.write(bi1, "png", new File(newn));
        } catch (IOException e) {
            System.err.println("Неможливо почати роботу. Упевніться, що файл " +
                    "colors.properties лежить у тій самій папці, що і програма.\n" +
                    "Можливо, ваша картинка має 4 канали замість 3-ох. Тоді " +
                    "перетворіть її у JPG формат.");
        }
    }

    public static void main(String[] args) throws IOException {
        new Reimag();
    }
}