import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DragNDrop extends JFrame implements MouseListener
{
    private ArrayList<JButton> btnArray = new ArrayList<JButton>();
    private int k = 0;
    private int W = 100;
    private int H = 30;

    public DragNDrop()
    {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        addMouseListener(this);

        JLabel l = new JLabel("One click - add button \n" +
                "Double click - remove button");
        add(l);
        l.setBounds(0, 0, 300, 100);
    }

    public static void main(String[] args)
    {
        DragNDrop dragNDrop = new DragNDrop();
        dragNDrop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dragNDrop.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println(k);
        btnArray.add(new JButton("Button " + k));
        this.add(btnArray.get(btnArray.size() - 1));
        btnArray.get(btnArray.size() - 1).setBounds(e.getX() - W/2, e.getY() - 2*H, W, H);
        btnArray.get(btnArray.size() - 1).addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                super.mouseDragged(e);
                JButton b = (JButton) e.getComponent();
                try
                {
                    int x =  (int) getThis().getMousePosition().getX() - W/2;
                    int y = (int) getThis().getMousePosition().getY() - 50;
                    b.setBounds(x, y, W, H);
                } catch (NullPointerException e1){}
            }
        });
        btnArray.get(btnArray.size() - 1).addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                if (e.getClickCount() == 2)
                {
                    JButton b = (JButton) e.getComponent();
                    for (int i = 0; i < btnArray.size(); i++)
                    {
                        if (b.equals(btnArray.get(i)))
                        {
                            System.out.println(btnArray.get(i).getText());
                            getThis().remove(b);
                            btnArray.remove(i);
                            getThis().repaint();
                            break;
                        }
                    }
                }
            }
        });
        k++;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public DragNDrop getThis()
    {
        return this;
    }

}
