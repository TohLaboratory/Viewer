// PrgrssBar.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrgrssBar extends Application {

    // �v���O���X�o�[
    ProgressBar progressBar = new ProgressBar();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PrgrssBar");
        stage.setWidth(380);
        stage.setHeight(120);

        // �v���O���X�o�[�̐ݒ�
        progressBar.setProgress(0.0);
        progressBar.setPrefWidth(350);
        progressBar.setMaxWidth(350);

        // �X�e�[�^�X�o�[
        Label statusBar = new Label("[Start]���N���b�N���Ă��������B");
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);

        // [Start]�{�^��
        Button button = new Button("Start");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                final Task<String> task = getTask();
                progressBar.progressProperty().unbind();
                progressBar.progressProperty().bind(task.progressProperty());
                statusBar.textProperty().bind(task.messageProperty());
                final ExecutorService exe = Executors.newSingleThreadExecutor();
                exe.submit(task);
                task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                    new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        exe.shutdown();
                    }
                });
            }
        });


        VBox root = new VBox();
        root.getChildren().addAll(button, progressBar, statusBar);

        stage.setScene(new Scene(root));
        stage.show();
    }

    // ���Ԃ��������Ƃ̃V�~�����[�g
    private Task<String> getTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                updateMessage("Start");
                double done = 0.0;
                while (done < 100.1) {
                    updateProgress(done, 100);
                    Thread.sleep(200);
                    String s = String.format("%d%% ����", (int)done);
                    updateMessage(s);
                    done += 5;
                }
                updateMessage("����");
                return "Done";
            }
        };
    }
}
