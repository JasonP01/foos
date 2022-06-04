package mindustry.client.utils;

import arc.struct.Seq;
import kotlin.text.Regex;
import static mindustry.Vars.*;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.blocks.logic.LogicBlock.LogicBuild;

public class ProcessorFinder {
    private static final Seq<LogicBuild> highlighted = new Seq<>();

    public static void query(Regex query) {
        Seq<Building> builds = new Seq<>();
        player.team().data().buildings.getObjects(builds);

        int matchCount = 0, processorCount = 0;
        for (Building build : builds) {
            if (build instanceof LogicBuild logicBuild ) {
                if (query.containsMatchIn((logicBuild.code))) {
                    matchCount++;
                    highlighted.add(logicBuild);
                }
                processorCount++;
            }
        }

        // Log how many found
        if (matchCount == 0) player.sendMessage("[accent]No matches found.");
        else player.sendMessage(String.format("[accent]Found [coral]%d/%d[] [accent]matches.", matchCount, processorCount));
    }

    public static void clear() {
        highlighted.clear();
    }
    
    public static int getCount() {
        return highlighted.size;
    }

    public static void draw() {
        for (LogicBuild build : highlighted) {
            Drawf.square(build.x, build.y, build.block.size * tilesize/2f + 2f);
        }
    }
}
