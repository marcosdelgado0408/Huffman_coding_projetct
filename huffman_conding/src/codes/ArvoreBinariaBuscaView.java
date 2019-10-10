package codes;


import javax.swing.*;
import java.awt.*;

public class ArvoreBinariaBuscaView extends JComponent {

    private HeapMix arvore = null;
    private int nodeSize = 30;
    private int offset   = 60;

    public ArvoreBinariaBuscaView(HeapMix arvore) {
        this.arvore = arvore;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (this.arvore == null) {
            return;
        }
        drawNode(
                graphics,
                this.arvore.getRoot(),
                getWidth() / 2,
                0,
                0 //nível
        );
    }

    private void drawNode(Graphics graphics, Node node, int x, int y, int level) {
        graphics.drawOval(x, y, nodeSize, nodeSize);
        graphics.drawString(
                String.valueOf(node.getCount()),
                x + 8,
                y + 20
        );

        if (node.getLeft() != null) {
            int childX = x - offset;
            int childY = y + offset;

            graphics.drawLine(
                    x + nodeSize / 2,
                    y + nodeSize,
                    childX + nodeSize / 2,
                    childY
            );
            drawNode(graphics, node.getLeft(), childX, childY, level + 1);
        }

        if (node.getRight() != null) {
            int childX = x + offset;
            int childY = y + offset;

            graphics.drawLine(
                    x + nodeSize / 2,
                    y + nodeSize,
                    childX + nodeSize / 2,
                    childY
            );

            drawNode(graphics, node.getRight(), childX, childY, level + 1);
        }



    }
}
