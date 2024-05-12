import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class JSplitPaneExample {
    public static void main(String[] args) {
        // 創建 JFrame 實例
        JFrame frame = new JFrame("JSplitPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // 設定窗口的大小

        // 創建文本區域和滾動條
        JTextArea textArea = new JTextArea("這裡是文本區域");
        JScrollPane scrollPaneLeft = new JScrollPane(textArea);

        // 創建列表和滾動條
        String[] data = {"項目 1", "項目 2", "項目 3", "項目 4"};
        JList<String> list = new JList<>(data);
        JScrollPane scrollPaneRight = new JScrollPane(list);

        // 創建 JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                              scrollPaneLeft, scrollPaneRight);
        splitPane.setOneTouchExpandable(true); // 允許快速展開/折叠面板

        // 在窗口顯示後設置分割條的位置為一半
        SwingUtilities.invokeLater(() -> {
            splitPane.setDividerLocation(0.5);
        });

        // 添加 JSplitPane 到 JFrame
        frame.getContentPane().add(splitPane);

        // 設定窗口為可見
        frame.setVisible(true);
    }
}
