/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tournament.impl.executor;

import java.util.List;

/**
 *
 * @author Tobias
 */
public interface MatchExecutor
{
    public void executeAll(List<Runnable> matches);
}
