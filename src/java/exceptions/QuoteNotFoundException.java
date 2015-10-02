package exceptions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author martamiszczyk
 */
public class QuoteNotFoundException extends Exception
{

    public QuoteNotFoundException(String Quote_with_requested_id_not_found)
    {
        super(Quote_with_requested_id_not_found);
    }
}
