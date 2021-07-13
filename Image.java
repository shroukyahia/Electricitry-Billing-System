package customers;
import javax.swing.*;
public class Image extends JFrame  {
       String pic;
        public  void Image()
        {
            JFrame f = new JFrame();
            
        String fileName = pic;
        ImageIcon icon = new ImageIcon(fileName);
        JLabel label = new JLabel(icon);
        f.getContentPane().add(new JScrollPane(label));
        f.setSize(1000,600);
        f.setResizable(false);
        f.setVisible(true);
        f.pack();
        f.setTitle("Your Contract ");
    }
}
    

