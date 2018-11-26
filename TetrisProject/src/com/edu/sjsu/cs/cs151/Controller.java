package com.edu.sjsu.cs.cs151;

public class Controller {
    private View view;
    private Model model;
    private HoldBlockView nextBlockView;
    Model.NextTetrominoGenerator nextTetrominoGenerator;


    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        nextTetrominoGenerator = model.new NextTetrominoGenerator();
        nextBlockView = view.mainGameView.getNextBlock();
        nextBlockView.inputTetromino(nextTetrominoGenerator.generateRandom());
    }

    public void mainLoop()
    {
        //give a new model tetromino to nextBlockView Object
    }
}
