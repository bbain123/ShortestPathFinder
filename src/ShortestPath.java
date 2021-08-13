/**
 * Class used for path navigation
 * @author Brendan Bain 251086487
 */
public class ShortestPath {
	CityMap cityMap;

	/**
	 * Constructor of ShortestPath. Initializes cityMap as theMap
	 * @param theMap the map to be used to find a path
	 */
	public ShortestPath (CityMap theMap) {
		cityMap = theMap;
	}
	
	/**
	 * Method used to find the shortest path. If path is found, length of path
	 * is printed.
	 */
	public void findShortestPath() {
		OrderedCircularArray<MapCell> orderedList = new OrderedCircularArray<MapCell>();
		orderedList.insert(cityMap.getStart(), 0);
		cityMap.getStart().markInList();
		boolean destinationReached = false;
		int returnDistance = 0;
		
		
		while (!orderedList.isEmpty() & !destinationReached) {
			MapCell smallest = orderedList.getSmallest();
			returnDistance = smallest.getDistanceToStart() + 1;
			smallest.markOutList();
			if (smallest.isDestination())
				destinationReached = true;
			else {
				
				while (nextCell(smallest) != null) {
					int distanceD = 1 + smallest.getDistanceToStart();
					int pointP;
					
					if (nextCell(smallest).getDistanceToStart() > distanceD) {
						nextCell(smallest).setDistanceToStart(distanceD);
						nextCell(smallest).setPredecessor(smallest);
					}
					pointP = nextCell(smallest).getDistanceToStart();
					
					if (nextCell(smallest).isMarkedInList()) {
						if (pointP < orderedList.getValue(nextCell(smallest)))
							orderedList.changeValue(nextCell(smallest), pointP);
					}
					
					else if (!nextCell(smallest).isMarkedInList()) {
						orderedList.insert(nextCell(smallest), pointP);
						nextCell(smallest).markInList();
					}
				}	
			}		
		}
		if (destinationReached)
			System.out.println("Path found of length " + returnDistance);
		else
			System.out.println("No path found");
	}
	
	/**
	 * Method used to get the next available cell
	 * @param cell the current cell
	 * @return the next available cell that could be used to continue the path. Null if no more cells
	 */
	private MapCell nextCell(MapCell cell) {
		for (int i = 0; i < 4; i++) {
			if (cell.getNeighbour(i) != null) {
				if (!(cell.getNeighbour(i)).isMarked()) {
					if ((cell.isNorthRoad()||cell.isIntersection()||cell.isStart()) & (cell.getNeighbour(i).isIntersection()||cell.getNeighbour(i).isNorthRoad()||cell.getNeighbour(i).isDestination()) & i == 0)
						return cell.getNeighbour(0);
					if ((cell.isEastRoad()||cell.isIntersection()||cell.isStart()) & (cell.getNeighbour(i).isIntersection()||cell.getNeighbour(i).isEastRoad()||cell.getNeighbour(i).isDestination()) & i == 1)
						return cell.getNeighbour(1);
					if ((cell.isSouthRoad()||cell.isIntersection()||cell.isStart()) & (cell.getNeighbour(i).isIntersection()||cell.getNeighbour(i).isSouthRoad()||cell.getNeighbour(i).isDestination()) & i == 2)
						return cell.getNeighbour(2);
					if ((cell.isWestRoad()||cell.isIntersection()||cell.isStart()) & (cell.getNeighbour(i).isIntersection()||cell.getNeighbour(i).isWestRoad()||cell.getNeighbour(i).isDestination()) & i == 3)
						return cell.getNeighbour(3);
				}
			}		
		}
		return null;
	}
}
