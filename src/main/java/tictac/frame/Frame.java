package tictac.frame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import tictac.board.Board;

@SuppressWarnings("serial")
public class Frame extends JFrame implements MouseListener {
    private Board board;

    private final int TILE_SIZE = 200;
    private final Font font;
    private String message;
    private boolean changed;

    private boolean player;
    private boolean complete;
    private boolean restart;

    public Frame(Board b) {
        restart = false;

        board = b;

        setSize(800, 800);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        message = "X's Turn";

        addMouseListener(this);

        font = new Font("Calibri", Font.BOLD, 40);

        changed = false;

        player = true;

        complete = false;

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.repaint();

        if (restart) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            restart = false;
        }

        if (changed) {
            g.clearRect(0, 0, this.getWidth(), 100);
            changed = false;
        }

        g.setFont(font);
        g.drawString(message, 100, 75);

        board.Draw(g, TILE_SIZE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!complete) {
            int newBox = board.getBox(e.getX(), e.getY(), TILE_SIZE);

            if (!board.Insert(newBox, player)) {
                message = "Invalid Move. ".concat(player ? "X's Turn" : "O's Turn");
            } else {
                player = !player;
                message = player ? "X's Turn" : "O's Turn";
            }

            if (board.CheckWin()) {
                player = !player;
                message = (player ? "X" : "O").concat(" wins the game!");
                complete = true;
            }

            if (board.checkTie()) {
                message = "It's A Tie!";
                complete = true;
            }

            changed = true;
            repaint();
        } else {
            board = new Board();
            changed = true;

            player = true;

            message = "X's Turn";
            complete = false;

            restart = true;

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
