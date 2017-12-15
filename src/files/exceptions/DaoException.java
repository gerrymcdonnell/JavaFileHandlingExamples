/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files.exceptions;

/**
 *
 * @author gerry
 */
import java.io.IOException;


public class DaoException extends IOException {

    public DaoException() {
    }

    public DaoException(String aMessage) {
        super(aMessage);
    }
}
