import com.google.gson.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public class test {
    public test() {
        helloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String testString = getJsonString();
                    textArea1.setWrapStyleWord(true);
                    textArea1.setLineWrap(true);
                    textArea1.setText(testString);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.isResizable();
        frame.setVisible(true);
    }

    public String getJsonString() throws IOException {
        URL url = new URL("https://api.github.com/users/hadley/orgs");
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), UTF_8))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
        }
        String jsonStr = builder.toString();
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(jsonStr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJsonString = gson.toJson(json);
        System.out.println(prettyJsonString);

        return prettyJsonString;
    }

    private JPanel rootPanel;
    private JButton helloButton;
    private JTextField nameTextField;
    private JTextArea textArea1;
}
