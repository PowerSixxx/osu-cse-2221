import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Baowen Liu
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model, NNCalcView view) {
        // Get current input and output numbers from the model
        NaturalNumber input = model.bottom();
        NaturalNumber output = model.top();

        // Update the displays with these numbers
        view.updateBottomDisplay(input);
        view.updateTopDisplay(output);

        boolean allowed = true;
        // Check to see if division is allowed
        // Division is allowed only if the input (denominator) is greater than 0
        if (input.compareTo(new NaturalNumber2()) > 0) {
            // Input > 0 -> division allowed
            allowed = true;
            view.updateDivideAllowed(allowed);
        } else {
            // Input == 0 -> division not allowed
            allowed = false;
            view.updateDivideAllowed(allowed);
        }
        // Check if subtraction is allowed
        // Subtraction is allowed only if input (bottom) <= output (top)
        if (input.compareTo(model.top()) < 0) {
            // Input < Top -> subtraction allowed
            allowed = true;
            view.updateSubtractAllowed(allowed);
        } else {
            // Input >= Top -> subtraction not allowed
            allowed = false;
            view.updateSubtractAllowed(allowed);
        }
        // Check if power is allowed
        // Power is allowed only if input (exponent) <= Integer.MAX_VALUE
        if (input.compareTo(INT_LIMIT) <= 0) {
            // Input within integer limit -> power allowed
            allowed = true;
            view.updatePowerAllowed(allowed);
        } else {
            // Input exceeds integer limit -> power not allowed
            allowed = false;
            view.updatePowerAllowed(allowed);
        }
        // Check if root is allowed:
        // Root is allowed only if input >= 2 and <= Integer.MAX_VALUE
        if (input.compareTo(TWO) >= 0 && input.compareTo(INT_LIMIT) <= 0) {
            // Input is within valid root range -> root allowed
            allowed = true;
            view.updateRootAllowed(allowed);
        } else {
            // Input out of range for root -> root not allowed
            allowed = false;
            view.updateRootAllowed(allowed);
        }

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Set the top value to the bottom value
        top.transferFrom(bottom);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Add top to bottom and clear top
        bottom.add(top);
        top.clear();
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Subtract bottom from top, then copy result to bottom
        top.subtract(bottom);
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Multiply top by bottom, then copy result to bottom
        top.multiply(bottom);
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Divide top by bottom; remainder is returned
        NaturalNumber r = top.divide(bottom);
        // Copy quotient to bottom
        bottom.transferFrom(top);
        // Copy remainder to top
        top.transferFrom(r);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Convert the bottom number to an integer
        int lower = bottom.toInt();
        // Raise top to the power of the bottom value
        top.power(lower);
        // Copy result to bottom and clear top
        bottom.transferFrom(top);

        // Update the view
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRootEvent() {
        // Get top and bottom values
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Convert the bottom number to integer
        int lower = bottom.toInt();
        // Take the root of the top
        top.root(lower);
        // Transfer the bottom number from the top
        bottom.transferFrom(top);

        // Update the view
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        /*
         * Get aliases to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.multiplyBy10(digit);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

}
