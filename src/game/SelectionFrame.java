package game;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class SelectionFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean selected;

    final JPanel customPanel = new JPanel();

    ArrayList<Integer> customGroup;
    HeroGroup heroes;

    JButton selection = new JButton();

    public SelectionFrame(int h, int w){
        setBounds(0, 0, h, w);
        setResizable(true);
        customGroup = new ArrayList<Integer>();
        this.setTitle("Group Selection");
    }

    public void addComponents(){

        //customPanel
        customPanel.setLayout(new GridLayout(0,1,4,4));
        JButton[] chars = new JButton[4];
        chars[0] = new JButton("BEAR");
        chars[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                customGroup.add(1);

            }
        });
        chars[1] = new JButton("SPIRIT");
        chars[1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                customGroup.add(2);

            }
        });
        chars[2] = new JButton("G.I. JOE?");
        chars[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                customGroup.add(3);
            }
        });
        chars[3] = new JButton("Aang");
        chars[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customGroup.add(4);
            }
        });


        for(JButton c: chars) customPanel.add(c);
    }

    public HeroGroup display(){
        addComponents();
        this.setTitle("Select three characters");
        customPanel.setVisible(true);
        this.add(customPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Integer i = 0;
        while(heroes == null){
            if(customGroup.size()==3) heroes = new HeroGroup(customGroup.get(0), customGroup.get(1), customGroup.get(2));
            i++;
        }

        exit();
        return heroes;

    }


    public void exit(){
        this.setVisible(false);
        this.dispose();
    }

}
