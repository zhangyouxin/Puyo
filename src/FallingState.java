
public class FallingState extends BlockState{

	public static int FallingStateID = 1;
	
	int mStepsToFall;
	int mStartingPosY;
	PuzzleField mField;
	public FallingState(PuzzleBlock block)
	{
		super(block);
		StateID = FallingStateID;
		mStepsToFall = -1;
	}
	
	public FallingState(PuzzleBlock block, int steps)
	{
		super(block);
		mStepsToFall = steps;
		mStartingPosY = block.getY();
		StateID = FallingStateID;
	}
	
	public void update(float time)
	{
		Block.Move(0, PuzzleBlock.FallSpeed*time);
		
		if(mStepsToFall != -1)
		{
			if((Block.getY() - mStartingPosY) / PuzzleBlock.BLOCK_H >= mStepsToFall)
			{
				int yindex = Block.getY()%32;
				Block.SetPosition(Block.getX(),Block.getY()+yindex);
				Block.StateIsChanged(true);
				Block.ChangeState(new IdleState(Block));
			}
		}
	}
	
	public void onExit()
	{
		Block.SnapToPosition(Block.getX(), Block.getY());
		Block.StateIsChanged(true);
		System.out.print(" exit falling ");
	}
}
