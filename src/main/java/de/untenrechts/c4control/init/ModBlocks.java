package de.untenrechts.c4control.init;

import de.untenrechts.c4control.overworld.BlockATM;


public class ModBlocks {
	
		private static Block atmBlock;

		public static void init() {
			atmBlock = new BlockATM();
		}
}
