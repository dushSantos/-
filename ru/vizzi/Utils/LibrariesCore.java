package ru.vizzi.Utils;

import java.util.concurrent.Callable;

import com.google.common.util.concurrent.ListenableFuture;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import ru.vizzi.Utils.resouces.CoreAPI;
import ru.vizzi.Utils.resouces.PreLoadableResourceManager;
import ru.vizzi.Utils.resouces.TextureLoader;


@Mod(modid = LibrariesCore.MODID, name = LibrariesCore.MODNAME, version = LibrariesCore.VERSION)
public class LibrariesCore {

	@Instance(LibrariesCore.MODID)
	public static LibrariesCore Instance;
	
	public final TextureLoader textureLoader = new TextureLoader();
	
	public static final String MODID = "LibrariesCore";
	public static final String MODNAME = "LibrariesCore";
	public static final String VERSION = "1.0.0";
	private boolean isClient = FMLCommonHandler.instance().getSide().isClient();
	private Logger logger = new Logger(MODID);
	

	@EventHandler
	   public void preInit(FMLPreInitializationEvent event) {

		SyncResultHandler.setMainThreadExecutor(this::runUsingMainThread);
	      if(isClient) {
	    	  CoreAPI.init();
		      CommonUtils.registerEvents(PreLoadableResourceManager.class);
		      FMLCommonHandler.instance().bus().register(this);
		      MinecraftForge.EVENT_BUS.register(this);
	      };
	      
	      
	   }
	@EventHandler
	   public void Init(FMLInitializationEvent event) {
	
	   }
	@EventHandler
	   public void postInit(FMLPostInitializationEvent event) {
		 if(this.isClient) {
			 MinecraftForge.EVENT_BUS.post(new EventLoadResource());
		 }
	    
	      
	   }
	@SubscribeEvent
    public void event(TickEvent.ClientTickEvent event) {
        if(event.phase == TickEvent.Phase.START) {
            CompletableFutureBuilder.SyncQueueHandler.update();
        }
    }
	@SuppressWarnings("unchecked")
    public <T> ListenableFuture<T> runUsingMainThread(Runnable runnable) {
        return (ListenableFuture<T>) Minecraft.getMinecraft().func_152344_a(runnable);
    }

    @SuppressWarnings("unchecked")
    public <T> ListenableFuture<T> runUsingMainThread(Callable<T> runnable) {
        return (ListenableFuture<T>) Minecraft.getMinecraft().func_152343_a(runnable);
    }
}
