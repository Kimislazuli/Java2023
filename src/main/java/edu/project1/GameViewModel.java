package edu.project1;

public class GameViewModel {
    private String[] data;
    private final String[] dictionary = {"apple", "pie", "waterfall"};
    private final GameModel model = new GameModel(dictionary);

    public void setData(String userInput) {
        this.data = processDataInModel(userInput);
    }

    public String[] getUpdateFromViewModel() {
        return data;
    }

    private String[] processDataInModel(String userInput) {
        return model.processInput(userInput);
    }

    public String getWordMask() {
        return model.getWordMask();
    }
}
