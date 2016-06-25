# An Item decoration class for google design lib's RecyclerView

## What's it for?
This class is for decorating a recyclerview's item that the decorator has deemed as "Selected"
the criteria for selection is decided by the decorator when it calls the recyclerview adapter's 
`isSelected(int position)` method. Therefore as a pre-requisite you must implement the SelectableAdapter 
interface in the `Recyclerview`'s adapter to which you are adding the decoration to. 

Please check the sample app for a demonstration on the usage of the item decoration class.