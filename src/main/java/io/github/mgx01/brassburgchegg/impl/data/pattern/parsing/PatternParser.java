package io.github.mgx01.brassburgchegg.impl.data.pattern.parsing;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import java.util.List;

public class PatternParser {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ParsedActionPattern parse(List<String> rawGrid, String entityName) {
        ParsedActionPattern pattern = new ParsedActionPattern();

        if (rawGrid == null || rawGrid.isEmpty()) {
            return pattern;
        }
        int[] origin = findOriginLocation(rawGrid, entityName);

        if (origin == null) {
            return pattern;
        }
        parseRelativeOffsets(rawGrid, pattern, origin[0], origin[1], entityName);

        return pattern;
    }

    private static int[] findOriginLocation(List<String> rawGrid, String entityName) {
        for (int row = 0; row < rawGrid.size(); row++) {
            String line = rawGrid.get(row);
            int colIndex = line.indexOf('O');
            if (colIndex != -1) {
                return new int[]{row, colIndex};
            }
        }

        LOGGER.error("BrassburgChegg: CRITICAL - No Origin 'O' found in action_pattern for entity '{}'!", entityName);
        return null;
    }

    private static void parseRelativeOffsets(List<String> rawGrid, ParsedActionPattern pattern, int originRow, int originCol, String entityName) {
        for (int row = 0; row < rawGrid.size(); row++) {
            String line = rawGrid.get(row);
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);

                if (c == '-' || c == 'O' || c == ' ') continue;

                int relX = col - originCol;
                int relZ = row - originRow;

                switch (c) {
                    case 'M':
                        pattern.addMove(relX, relZ);
                        break;
                    case 'A':
                        pattern.addAttack(relX, relZ);
                        break;
                    case 'B':
                        pattern.addMove(relX, relZ);
                        pattern.addAttack(relX, relZ);
                        break;
                    case 'X':
                        pattern.addSpecial(relX, relZ);
                        break;
                    case 'S':
                        pattern.addMove(relX,relZ);
                        pattern.addSpecial(relX,relZ);
                        break;
                    default:
                        LOGGER.warn("BrassburgChegg: Unknown character '{}' in action_pattern for entity '{}'. Ignoring.", c, entityName);
                }
            }
        }
    }
}