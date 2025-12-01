import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Baowen Liu
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> output = model.output();

        StringBuilder text = new StringBuilder();

        for (String x : output) {
            text.insert(0, x);
        }
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        view.updateOutputDisplay(text.toString());
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        this.model.output().clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes append event.
     */
    @Override
    public void processAppendEvent(String input) {
        this.model.setInput("");
        this.model.output().push(input);
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes undo event.
     */
    @Override
    public void processUndoEvent() {
        String x = this.model.output().pop();
        this.model.setInput(x);
        updateViewToMatchModel(this.model, this.view);
    }
}
