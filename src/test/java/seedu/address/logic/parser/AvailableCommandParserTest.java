package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGN_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AvailableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.DateMatchesPredicate;

public class AvailableCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AvailableCommand.MESSAGE_USAGE);

    private final AvailableCommandParser parser = new AvailableCommandParser();

    /**
     * Parses {@code input} into a {@code DateMatchesPredicate}.
     */
    private DateMatchesPredicate preparePredicate(String input) throws ParseException {
        return new DateMatchesPredicate(ParserUtil.parseDate(input));
    }

    @Test
    public void parse_noArgs_failure() {
        // no date specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnsAvailableCommand() throws ParseException {
        AvailableCommand expectedCommand = new AvailableCommand(preparePredicate(VALID_DATE));
        assertParseSuccess(parser, ASSIGN_DATE, expectedCommand);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix
        assertParseFailure(parser, " a/" + VALID_DATE, MESSAGE_INVALID_FORMAT);

        // invalid prefix as preamble
        assertParseFailure(parser, " random string" + VALID_DATE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, INVALID_DATE_DESC, DateParserUtil.MESSAGE_CONSTRAINTS);
    }
}
