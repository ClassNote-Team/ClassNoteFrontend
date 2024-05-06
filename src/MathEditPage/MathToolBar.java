package MathEditPage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

public class MathToolBar extends JToolBar{

    private MathButton greekSymbolButton;
    private MathButton calculusButton;
    private MathButton MathSymbolButton;

    public void createToolBar(int width) {

        greekSymbolButton = new MathButton();
        greekSymbolButton.createButton("\\alpha\\beta\\gamma\\delta", 18);
        greekSymbolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action here
                System.out.println(greekSymbolButton.getHeight());
            }
        });
        add(greekSymbolButton);

        calculusButton = new MathButton();
        calculusButton.createButton("\\int\\sum\\prod\\bigcup", 9);
        calculusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(calculusButton.getHeight());
            }
        });
        add(calculusButton);

        MathSymbolButton = new MathButton();
        MathSymbolButton.createButton("+-\\times\\div", 18);
        MathSymbolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(MathSymbolButton.getHeight());
            }
        });
        add(MathSymbolButton);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setFloatable(false);
        setPreferredSize(new Dimension(width, getPreferredSize().height));

        validate();
        repaint();
    }
}
