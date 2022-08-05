package mindustry.client.navigation;

import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.gen.*;

import static mindustry.Vars.*;

public class RepairPath extends Path {
    Building current;
    Interval delay = new Interval();

    @Override
    public void init() {
        super.init();

        addListener(() -> player.shooting(false));
    }

    @Override
    public void setShow(boolean show) {}

    @Override
    public boolean getShow() {
        return false;
    }

    @Override
    public void follow() {
        Building build = Units.findDamagedTile(player.team(), player.x, player.y);
        if (build == null || player.unit() == null || (build != current && !delay.check(0, 5))) return;
        current = build;
        delay.reset(0, 0);
        player.shooting(player.unit().inRange(build) && !player.unit().activelyBuilding() && !player.unit().mining());
        player.unit().aimLook(build);

        goTo(build, 16f, tilesize * 3);
    }

    @Override
    public float progress() {
        return Mathf.num(Units.findDamagedTile(player.team(), player.x, player.y) == null);
    }

    @Override
    public void reset() {}

    @Override
    public Position next() {
        return null;
    }

    @Override
    public synchronized void draw() {
        waypoints.draw();
    }
}
