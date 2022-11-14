/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */
package Q1;

/***
 * interface for AnimalFactory and PlantFactory
 */
public interface AbstractSeaFactory
{
    SeaCreature produceSeaCreature(String type);
}
