package bridge;

import static bridge.constant.BridgeSize.MAX_BRIDGE_SIZE;
import static bridge.constant.BridgeSize.MIN_BRIDGE_SIZE;
import static bridge.constant.Commands.DOWN_COMMAND;
import static bridge.constant.Commands.UP_COMMAND;
import static bridge.constant.ExceptionName.BRIDGE_MAKER_SIZE_EXCEPTION;

import java.util.ArrayList;
import java.util.List;
import view.InputView;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public int readBridgeSize() {
        String bridgeSize = new InputView().readBridgeSize();
        return validateBridgeSize(bridgeSize);
    }

    private int validateBridgeSize(String bridgeSize) {
        int integerBridgeSize = validateBridgeSizeInteger(bridgeSize);
        validateBridgeSizeRange(integerBridgeSize);
        return integerBridgeSize;
    }

    private int validateBridgeSizeInteger(String bridgeSize) {
        try {
            return Integer.parseInt(bridgeSize);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BRIDGE_MAKER_SIZE_EXCEPTION);
        }
    }

    private void validateBridgeSizeRange(int integerBridgeSize) {
        if (integerBridgeSize < MIN_BRIDGE_SIZE || integerBridgeSize > MAX_BRIDGE_SIZE) {
            throw new IllegalArgumentException(BRIDGE_MAKER_SIZE_EXCEPTION);
        }
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        List<String> bridge = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buildBridge(bridge);
        }
        return bridge;
    }

    private void buildBridge(List<String> bridge) {
        int randomNumber = bridgeNumberGenerator.generate();
        if (randomNumber == 0) {
            bridge.add(DOWN_COMMAND);
        } else if (randomNumber == 1) {
            bridge.add(UP_COMMAND);
        }
    }
}
