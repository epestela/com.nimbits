package com.nimbits.client.io.command;

import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.instance.Instance;
import com.nimbits.client.model.user.User;

import java.util.List;

public class RemoveCommand extends AbstractCommand implements Command {

    private final static String USAGE = "delete the current entity: rm <entity name> [-R]";

    public RemoveCommand(User user, Entity current, Instance server, List<Entity> tree) {
        super(user, current, server, tree);
    }

    @Override
    public void doCommand(CommandListener listener, String[] args) {
        boolean recursive = false;

        if (args.length < 2) {
            listener.onMessage(getUsage());

        } else {
            Entity entity = getEntity(args[1]);
            if (entity == null) {
                listener.onMessage(args[1] + " not found");
            } else {

                for (String s : args) {
                    if (s.equals("-R")) {
                        recursive = true;
                        break;
                    }
                }
                boolean hc = hasChildren(entity);

                if (hc && !recursive) {

                    listener.onMessage("entity has children. Use -R to delete it and all children.");
                } else {
                    tree.remove(entity);
                    //helper.deleteEntity(entity);
                    listener.onTreeUpdated(tree);

                }

            }

        }

    }

    private boolean hasChildren(Entity entity) {
        for (Entity e : tree) {
            if (e.getParent().equals(entity.getKey())) {

                return true;

            }
        }
        return false;

    }

    private Entity getEntity(String name) {
        for (Entity entity : tree) {
            if (entity.getName().getValue().equals(name)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public String getUsage() {
        return USAGE;
    }
}
